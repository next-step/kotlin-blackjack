package dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonTest {
    @ValueSource(strings = ["김다보미", "김마루"])
    @ParameterizedTest
    fun name(name: String) {
        val person =
            Person.introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person =
            Person.introduce {
                name("김다보미")
                company("한빛앤")
            }

        person.name shouldBe "김다보미"
        person.company shouldBe "한빛앤"
    }

    @Test
    fun skills() {
        val person =
            Person.introduce {
                name("김다보미")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        person.name shouldBe "김다보미"
        person.skills shouldBe listOf("A passion for problem solving", "Good communication skills", "Kotlin")
    }

    @Test
    fun languages() {
        val person =
            Person.introduce {
                name("김다보미")
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }

        person.name shouldBe "김다보미"
        person.languages shouldBe mapOf("Korean" to 5, "English" to 3)
    }

    @Test
    fun full() {
        val person =
            Person.introduce {
                name("김다보미")
                company("한빛앤")
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

        person.name shouldBe "김다보미"
        person.company shouldBe "한빛앤"
        person.skills shouldBe listOf("A passion for problem solving", "Good communication skills", "Kotlin")
        person.languages shouldBe mapOf("Korean" to 5, "English" to 3)
    }
}
