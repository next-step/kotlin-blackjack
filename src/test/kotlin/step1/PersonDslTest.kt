package step1

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("민동연")
 *   company("우아한형제들")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 3
 *   }
 * }
 */
class PersonDslTest {
    @Test
    fun nameTest() {
        val person =
            introduce {
                this.name("민동연")
            }
        person.name shouldBe "민동연"
    }

    @ValueSource(strings = ["민동연", "홍길동"])
    @ParameterizedTest
    fun nameTest2(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("민동연")
                company("우아한형제들")
            }
        person.company shouldBe "우아한형제들"
    }

    @Test
    fun skillsTest() {
        val person =
            introduce {
                name("민동연")
                company("우아한형제들")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        person.company shouldBe "우아한형제들"
    }

    @Test
    fun languageTest() {
        val person =
            introduce {
                name("민동연")
                company("우아한형제들")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        person.company shouldBe "우아한형제들"
    }
}
