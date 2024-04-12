use std::{fs, os::unix::fs::PermissionsExt, path::Path};

use anyhow::Result;
use jni::sys::{jboolean, JNI_FALSE, JNI_TRUE};
use sys_mount::{unmount, UnmountFlags};

pub const fn as_jboolean(b: bool) -> jboolean {
    if b {
        JNI_TRUE
    } else {
        JNI_FALSE
    }
}

#[allow(dead_code)]
pub const fn as_bool(b: jboolean) -> bool {
    match b {
        JNI_TRUE => true,
        JNI_FALSE => false,
        _ => unreachable!(),
    }
}

pub fn unlock_write<S: AsRef<str>, P: AsRef<Path>>(s: S, p: P) -> Result<()> {
    let s = s.as_ref();
    let p = p.as_ref();

    let _ = unmount(p, UnmountFlags::DETACH);
    let _ = fs::set_permissions(p, PermissionsExt::from_mode(0o644));
    fs::write(p, s)?;

    Ok(())
}
