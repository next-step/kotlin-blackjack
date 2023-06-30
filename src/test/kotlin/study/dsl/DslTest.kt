package study.dsl

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("이재윤")
 *   company(null)
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
internal class DslTest {

    @ValueSource(strings = ["이재윤", "제이슨"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
    }

    @ValueSource(strings = ["당근당근", "넥스트스텝"])
    @ParameterizedTest
    fun company(value: String) {
        val person = introduce {
            name("홍길동")
            company(value)
        }
        person.name shouldBe "홍길동"
        person.company shouldBe value
    }

    @Test
    fun companyNull() {
        val person = introduce {
            name("홍길동")
        }
        person.name shouldBe "홍길동"
        person.company.shouldBeNull()
    }

    @CsvSource("softSkill1, softSkill2, hardSkill", "soft sth, soft sth2, hard sth")
    @ParameterizedTest
    fun skills(softSkill1: String, softSkill2: String, hardSkill: String) {
        val person = introduce {
            name("name")
            company("company")
            skill {
                soft(softSkill1)
                soft(softSkill2)
                hard(hardSkill)
            }
        }

        person.skill.soft shouldContain softSkill1
        person.skill.soft shouldContain softSkill2
        person.skill.hard shouldContain hardSkill
    }

    @CsvSource("Korean, 5, English, 3", "German, 2, Spanish, 11")
    @ParameterizedTest
    fun languages(language1: String, language1Level: Int, language2: String, language2Level: Int) {
        val person = introduce {
            name("name")
            language {
                language1 level language1Level
                language2 level language2Level
            }
        }

        person.language.map[language1] shouldBe language1Level
        person.language.map[language2] shouldBe language2Level
    }
}
