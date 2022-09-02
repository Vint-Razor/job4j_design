package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknownObj() {
        final Box box = new Box(3, 10);
        String name = box.whatThis();
        assertThat(name).isNotEmpty()
                .isNotBlank()
                .doesNotContain("Sphere")
                .doesNotContain("Tetrahedron")
                .doesNotContain("Cube")
                .containsIgnoringCase("unknown")
                .isEqualTo("Unknown object");
    }

    @Test
    void isNumOfVerticesCubeThen8() {
        final Box cube = new Box(8, 10);
        int vert = cube.getNumberOfVertices();
        assertThat(vert).isPositive()
                .isEven()
                .isEqualTo(8);
    }

    @Test
    void isNumOfVerticesOctahedronMinus1() {
        final Box octahedron = new Box(12, 10);
        int vert = octahedron.getNumberOfVertices();
        assertThat(vert).isNegative()
                .isEqualTo(-1);
    }

    @Test
    void isExistTetrahedronThenTrue() {
        Box tetrahedron = new Box(4, 10);
        boolean exist = tetrahedron.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isExistOctahedronThenFalse() {
        Box octahedron = new Box(12, 10);
        boolean exists = octahedron.isExist();
        assertThat(exists).isFalse();
    }

    @Test
    void whenAreaSphereR10Then1256() {
        Box sphere = new Box(0, 10);
        double area = sphere.getArea();
        assertThat(area).isNotZero()
                .isGreaterThan(1255.0d)
                .isLessThan(1260.0d)
                .isCloseTo(1256.6d, withinPercentage(0.1d));
    }

    @Test
    void whenAreaUnknownThen0() {
        Box box = new Box(3, 10);
        double unk = box.getArea();
        assertThat(unk).isNotNegative()
                .isLessThan(1.0d)
                .isZero();
    }
}