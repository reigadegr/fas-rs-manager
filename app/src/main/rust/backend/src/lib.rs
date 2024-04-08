#![deny(clippy::all, clippy::pedantic)]
#![warn(clippy::nursery)]
#![allow(
    clippy::module_name_repetitions,
    clippy::cast_possible_truncation,
    clippy::cast_sign_loss,
    clippy::cast_precision_loss,
    clippy::cast_possible_wrap,
    non_snake_case,
    clippy::missing_panics_doc
)]

mod config;
mod powercfg;
mod tools;

use std::{fs, path::Path, ptr};

use jni::{
    objects::{JClass, JObject, JString},
    sys::{jboolean, jint, jobjectArray, jstring},
    JNIEnv,
};
use jni_fn::jni_fn;

use config::ConfigData;
use powercfg::PowerConfig;
use tools::{as_jboolean, unlock_write};

const NODE_DIR: &str = "/dev/fas_rs";
const CONFIG: &str = "/sdcard/Android/fas-rs/games.toml";

#[jni_fn("com.fasRs.manager.root.Native")]
pub fn isFasRsRunning(_: JNIEnv, _: JClass) -> jboolean {
    let pid = Path::new(NODE_DIR).join("pid");
    let Ok(pid) = fs::read_to_string(pid) else {
        return as_jboolean(false);
    };

    let comm = Path::new("/proc").join(pid).join("comm");
    let Ok(comm) = fs::read_to_string(comm) else {
        return as_jboolean(false);
    };

    as_jboolean(comm.trim() == "fas-rs")
}

#[jni_fn("com.fasRs.manager.root.Native")]
pub fn getFasRsMode(env: JNIEnv, _: JClass) -> jstring {
    let mode = Path::new(NODE_DIR).join("mode");
    let Ok(mode) = fs::read_to_string(mode) else {
        return ptr::null_mut();
    };

    env.new_string(mode).unwrap().into_raw()
}

#[jni_fn("com.fasRs.manager.root.Native")]
pub fn setFasRsMode(mut env: JNIEnv, _: JClass, mode: JString) {
    let target_mode: String = env.get_string(&mode).unwrap().into();
    let mode = Path::new(NODE_DIR).join("mode");

    let _ = unlock_write(target_mode, mode);
}

#[jni_fn("com.fasRs.manager.root.Native")]
pub fn getFasRsVersion(env: JNIEnv, _: JClass) -> jstring {
    let powercfg = Path::new("/data/powercfg.json");
    let Ok(powercfg) = fs::read_to_string(powercfg) else {
        return ptr::null_mut();
    };

    let Ok(powercfg) = serde_json::from_str::<PowerConfig>(&powercfg) else {
        return env.new_string("unknown").unwrap().into_raw();
    };

    if powercfg.name == "fas-rs" {
        let version = format!("{}({})", powercfg.version, powercfg.versionCode);
        env.new_string(version).unwrap().into_raw()
    } else {
        env.new_string("unknown").unwrap().into_raw()
    }
}

#[jni_fn("com.fasRs.manager.root.Native")]
pub fn getFasRsApps(mut env: JNIEnv, _: JClass) -> jobjectArray {
    let Ok(config) = fs::read_to_string(CONFIG) else {
        return ptr::null_mut();
    };
    let Ok(config) = toml::from_str::<ConfigData>(&config) else {
        return ptr::null_mut();
    };

    let apps: Vec<_> = config.game_list.into_keys().collect();

    let string_class = env.find_class("java/lang/String").unwrap();
    let array = env
        .new_object_array(apps.len() as jint, string_class, JObject::null())
        .unwrap();

    for (index, app) in apps.into_iter().enumerate() {
        env.set_object_array_element(&array, index as jint, env.new_string(app).unwrap())
            .unwrap();
    }

    array.into_raw()
}
