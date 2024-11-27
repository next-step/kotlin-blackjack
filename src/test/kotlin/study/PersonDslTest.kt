package study

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import person.introduce

class PersonDslTest {
    @ValueSource(strings = ["홍길동", "이산", "임꺽정"])
    @ParameterizedTest
    fun name(name: String) {
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
                name("홍길동")
                company("활빈당")
            }
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        person.skills.soft shouldContain "A passion for problem solving"
        person.skills.soft shouldContain "Good communication skills"
        person.skills.hard shouldContain "Kotlin"
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
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
        person.languages["Korean"] shouldBe 5
        person.languages["English"] shouldBe 3
    }
}
