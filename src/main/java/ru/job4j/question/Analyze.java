package ru.job4j.question;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        return new Info(addCount(previous, current), changCount(previous, current),
                delCount(previous, current));
    }

    private static int delCount(Set<User> prev, Set<User> curr) {
        Set<User> prevClone = new HashSet<>(prev);
        int rsl = 0;
        if (prevClone.retainAll(curr)) {
            rsl = prev.size() - prevClone.size();
        }
        return rsl;
    }

    private static int addCount(Set<User> prev, Set<User> curr) {
        Set<User> prevClone = new HashSet<>(prev);
        int rsl = 0;
        prevClone.addAll(prev);
        if (prevClone.addAll(curr)) {
            rsl = prevClone.size() - prev.size();
        }
        return rsl;
    }

    private static int changCount(Set<User> previous, Set<User> current) {
        int rsl = 0;
        Map<Integer, String> map = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User curr : current) {
            if (map.containsKey(curr.getId())
                    && !Objects.equals(map.get(curr.getId()), curr.getName())) {
                rsl++;
            }
        }
        return rsl;
    }
}
