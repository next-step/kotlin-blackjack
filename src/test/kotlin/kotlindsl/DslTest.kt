package kotlindsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["토미", "쿠크"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("토미")
            company("우아한고양이들")
        }
        assertThat(person.name).isEqualTo("토미")
        assertThat(person.company).isEqualTo("우아한고양이들")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("토미")
            company("우아한고양이들")
            skills {
                soft("밥주라고 야옹하기")
                hard("캣휠 마스터")
                hard("낚시대 놀이하기")
            }
        }
        assertThat(person.skills.soft).contains("밥주라고 야옹하기")
        assertThat(person.skills.hard).contains("캣휠 마스터")
        assertThat(person.skills.hard).contains("낚시대 놀이하기")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("토미")
            company("우아한고양이들")
            skills {
                soft("밥주라고 야옹하기")
                hard("캣휠 마스터")
                hard("낚시대 놀이하기")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages.languages.find { it == Pair("Korean", 5) }).isNotNull
        assertThat(person.languages.languages.find { it == Pair("English", 3) }).isNotNull
    }
}

