package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {

    @Test
    void checkProduce() {
        Generator generator = new OtherGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Jimmy");
        args.put("subject", "you");
        String actual = "I am a Jimmy, Who are you?";
        String expected = generator.produce(template, args);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whereKeysInTemplateNotInMapThereIAException() {
        Generator generator = new OtherGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("profession", "programmer");
        args.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("отсутствует один, или несколько ключей шаблона в аргументе \"args\"");
    }

    @Test
    void whereExtraKeysInMapThereIAException() {
        Generator generator = new OtherGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("profession", "programmer");
        args.put("subject", "you");
        args.put("name", "Jimmy");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("в аргументе \"args\" присутствуют лишние ключи");
    }
}