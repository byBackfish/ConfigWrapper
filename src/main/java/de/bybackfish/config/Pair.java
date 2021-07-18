package de.bybackfish.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<K, V>{
  K key;
  V value;
}
