package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
  name("이상인")
  company("넥스트스탭_코틀린")
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
 */
class DslTest {

    @ValueSource(strings = ["이상인", "위푸"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @ParameterizedTest
    @CsvSource(value = ["이상인, 넥스트스탭"])
    fun company(name: String, company: String) {
        val person: Person = introduce {
            name(name)
            company(company)
        }
        person.name shouldBe name
        person.company shouldBe company
    }

    @ParameterizedTest
    @CsvSource(value = ["A passion for problem solving, Good communication skills, Kotlin"])
    fun skills(soft1: String, soft2: String, hard: String) {
        val person: Person = introduce {
            name("이상인")
            company("넥스트스탭")
            skills {
                soft(soft1)
                soft(soft2)
                hard(hard)
            }
        }
        person.skills.softs[0] shouldBe soft1
        person.skills.softs[1] shouldBe soft2
        person.skills.hards[0] shouldBe hard
    }

    @ParameterizedTest
    @CsvSource(value = ["Korean, 5, English, 3"])
    fun languages(language1: String, level1: Int, language2: String, level2: Int) {
        val person: Person = introduce {
            name("이상인")
            company("넥스트스탭")
            languages {
                language1 level level1
                language2 level level2
            }
        }

        person.language.levels[0].language shouldBe language1
        person.language.levels[0].level shouldBe level1
        person.language.levels[1].language shouldBe language2
        person.language.levels[1].level shouldBe level2
    }
}
