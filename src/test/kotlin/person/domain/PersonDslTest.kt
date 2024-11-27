package person.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import person.domain.person.Person
import person.domain.person.language.Language
import person.domain.person.language.Languages
import person.domain.person.skill.Skill
import person.domain.person.skill.Skills

class PersonDslTest : StringSpec({
    "introduce는 Person을 반환한다." {
        val person =
            PersonDsl.introduce {
                name("김성민")
                company("넥스트스텝")
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

        person.shouldBeInstanceOf<Person>()
        person.name shouldBe "김성민"
        person.company shouldBe "넥스트스텝"
        person.skills.skills shouldContainAll
            Skills(
                listOf(
                    Skill.HardSkill("Kotlin"),
                    Skill.SoftSkill("A passion for problem solving"),
                    Skill.SoftSkill("Good communication skills"),
                ),
            ).skills
        person.languages.values shouldContainAll
            Languages(
                listOf(
                    Language("Korean", 5),
                    Language("English", 3),
                ),
            ).values
    }
})
