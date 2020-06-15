package com.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhanglifeng
 * @since 2020-06-12 10:31
 *
 * 将线程安全托管给ConcurrentHashMap
 * ThreadSafe
 */
public class DelegatingVehicleTracker {

    private final ConcurrentHashMap<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
//        return Collections.unmodifiableMap(new HashMap<>(locations));// 返回一个浅拷贝，应为point是不可变的
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        // 映射到某一个值时才替换， 返回旧值
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }

    public static void main(String[] args) {
        Map<String, Point> points = new HashMap<>();
        points.put("a", new Point(1, 2));
        points.put("b", new Point(2, 3));

        DelegatingVehicleTracker tracker = new DelegatingVehicleTracker(points);
        Map<String, Point> locationsView = tracker.getLocations();

        System.out.println(locationsView.get("a"));
        System.out.println(tracker.getLocation("a"));
        tracker.setLocation("a", 4, 5);
        System.out.println(locationsView.get("a"));
        System.out.println(tracker.getLocation("a"));

    }
}
