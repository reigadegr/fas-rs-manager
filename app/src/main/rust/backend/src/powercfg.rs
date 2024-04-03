use serde::{Deserialize, Serialize};

#[derive(Debug, Serialize, Deserialize, PartialEq, Eq)]
#[allow(non_snake_case)]
pub struct PowerConfig {
    pub name: String,
    pub version: String,
    pub versionCode: i64,
}
