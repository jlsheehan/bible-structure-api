package bible;

import java.util.HashMap;
import java.util.Map;

public class TwoKeyMap<B,C,V> {
    private Map<B, Map<C,V>> map;

    public TwoKeyMap() {
        map = new HashMap<>();
    }

    public V get(B firstKey, C secondKey) {
        Map<C,V> secondMap = map.get(firstKey);
        return secondMap.get(secondKey);
    }

    public void put(B firstKey, C secondKey, V value) {
        if (map.containsKey(firstKey)) {
            Map<C,V> secondMap = map.get(firstKey);
            secondMap.put(secondKey, value);
        } else {
            Map<C,V> secondMap = new HashMap<>();
            secondMap.put(secondKey, value);
            map.put(firstKey, secondMap);
        }
    }
}
