package study

import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ParameterizedTest
    @ValueSource(strings = ["조운현", "홍길동"])
    fun `name test`(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
        person.company shouldBe null
        person.skills shouldBe null
        person.languages shouldBe null
    }

    @ParameterizedTest
    @ValueSource(strings = ["kakao", "naver"])
    fun `company test`(company: String) {
        val person = introduce {
            name("조운현")
            company(company)
        }
        person.name shouldBe "조운현"
        person.company shouldBe company
        person.skills shouldBe null
        person.languages shouldBe null
    }

    @Test
    fun `skills test`() {
        val person = introduce {
            name("조운현")
            skills {
                soft(PASSION)
                soft(COMMUNICATION)
                hard(KOTLIN)
                hard(C_CPP)
            }
        }
        person.name shouldBe "조운현"
        person.skills?.introduce() shouldContain PASSION
        person.skills?.introduce() shouldContain COMMUNICATION
        person.skills?.introduce() shouldContain KOTLIN
        person.skills?.introduce() shouldContain C_CPP
        person.skills?.introduce() shouldNotContain JAVA
        person.languages shouldBe null
    }

    @Test
    fun `language test`() {
        val person = introduce {
            name("조운현")
            languages {
                KOREAN level 5
                JAPANESE level 1
            }
        }
        person.name shouldBe "조운현"
        person.languages?.introduce() shouldContain "Korean's level 5"
        person.languages?.introduce() shouldContain "Japanese's level 1"
        person.languages?.introduce() shouldNotContain "English"
        person.skills shouldBe null
    }

    @Test
    fun `dsl test`() {
        val person = introduce {
            name("조운현")
            company("kakao")
            skills {
                soft(PASSION)
                soft(COMMUNICATION)
                hard(C_CPP)
            }
            languages {
                KOREAN level 5
                ENGLISH level 3
            }
        }
        person.name shouldBe "조운현"
        person.company shouldBe "kakao"
        person.skills?.introduce() shouldContain PASSION
        person.skills?.introduce() shouldContain COMMUNICATION
        person.skills?.introduce() shouldContain C_CPP
        person.skills?.introduce() shouldNotContain JAVA
        person.languages?.introduce() shouldContain "Korean's level 5"
        person.languages?.introduce() shouldContain "English's level 3"
        person.languages?.introduce() shouldNotContain "Japanese"
    }

    companion object {
        const val PASSION = "A passion for problem solving"
        const val COMMUNICATION = "Good communication skills"
        const val KOTLIN = "Kotlin"
        const val JAVA = "Java"
        const val C_CPP = "C/C++"
        const val KOREAN = "Korean"
        const val ENGLISH = "English"
        const val JAPANESE = "Japanese"
    }
}
