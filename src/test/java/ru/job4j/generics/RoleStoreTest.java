package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("User"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role rsl = store.findById("10");
        assertNull(rsl);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.add(new Role("1", "Admin"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole(), is("User"));
    }

    @Test
    public void whenReplaceThenRoleIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("1", new Role("1", "Admin"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole(), is("Admin"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("10", new Role("10", "Admin"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole(), is("User"));
    }

    @Test
    public void whenDeleteUserThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.delete("1");
        Role rsl = store.findById("1");
        assertNull(rsl);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.delete("10");
        Role rsl = store.findById("1");
        assertThat(rsl.getRole(), is("User"));
    }
}