mod powercfg;
mod tools;

use std::{fs, path::Path, ptr};

use jni::{
    objects::{JClass, JString},
    sys::{jboolean, jstring},
    JNIEnv,
};
use jni_fn::jni_fn;

use powercfg::PowerConfig;
use tools::{as_jboolean, unlock_write};

const NODE_DIR: &str = "/dev/fas_rs";

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
