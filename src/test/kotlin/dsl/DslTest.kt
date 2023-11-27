package dsl

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["장효택", "장효택2"])
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @ParameterizedTest
    @ValueSource(strings = ["우아한형제들", "우아한형제들2"])
    fun company(company: String) {
        val person = introduce {
            name("장효택")
            company(company)
        }
        person.name shouldBe "장효택"
        person.company shouldBe company
    }

    @ParameterizedTest
    @MethodSource("skillPairs")
    fun skills(skill: Pair<String, String>) {
        // given
        val (soft, hard) = skill

        // when
        val person = introduce {
            name("장효택")
            skills {
                soft(soft)
                hard(hard)
            }
        }

        // then
        person.skills?.soft?.shouldContain(soft)
        person.skills?.hard?.shouldContain(hard)
    }

    @ParameterizedTest
    @MethodSource("languagePairs")
    fun languages(language: Pair<String, Int>) {
        // given
        val (name, level) = language

        // when
        val person = introduce {
            name("장효택")
            languages {
                name level level
            }
        }

        // then
        person.languages?.languages?.shouldContain(name to level)
    }

    companion object {
        @JvmStatic
        fun skillPairs() = listOf(
            "soft1" to "hard1",
            "soft2" to "hard2",
        )

        @JvmStatic
        fun languagePairs() = listOf(
            "korean" to 1,
            "japanese" to 2,
        )
    }
}
