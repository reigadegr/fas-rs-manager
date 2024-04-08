use std::collections::HashMap;

use serde::{Deserialize, Serialize};
use toml::Value;

#[derive(Debug, Serialize, Deserialize, Clone)]
pub struct ConfigData {
    pub game_list: HashMap<String, Value>,
}
