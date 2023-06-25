package study

import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import resume.builder.HardSkill
import resume.model.Person
import resume.introduce
import resume.builder.SoftSkill
import resume.model.Language
import resume.model.Languages
import resume.model.Skills

/**
 * introduce {
name("박재성")
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
 */
class DslTest {

    @ValueSource(strings = ["홍길동", "고영경"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("고영경")
            company("skp")
        }
        person.company shouldBe "skp"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("홍길동")
            company("sk")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills).hasSameClassAs(
            Skills(
                listOf(
                    SoftSkill("A passion for problem solving"),
                    SoftSkill("Good communication skills"),
                    HardSkill("Kotlin")
                )
            )
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("고영경")
            company("skp")
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

        assertThat(person.languages).hasSameClassAs(
            Languages(
                listOf(
                    Language("Korean", 5),
                    Language("English", 3),
                )
            )
        )

    }

}


