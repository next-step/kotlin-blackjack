package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class IntroduceDslTest : StringSpec({

    "Introduce Dsl을 통해 Person을 생성할 수 있다." {
        val name = "이진원"
        val company = "카카오스타일"

        val skillAlgorithm = "알고리즘" to SkillLevel.SOFT
        val skillJava = "Java" to SkillLevel.SOFT
        val skillKotlin = "Kotlin" to SkillLevel.HARD

        val koreanLanguage = "Korean" to 1
        val englishLanguage = "English" to -1

        val person: Person = introduce {
            name(value = name)
            company(value = company)

            skills {
                soft(value = skillAlgorithm.first)
                soft(value = skillJava.first)
                hard(value = skillKotlin.first)
            }

            languages {
                koreanLanguage.first level koreanLanguage.second
                englishLanguage.first level englishLanguage.second
            }
        }

        person.name shouldBe name
        person.company shouldBe company

        person.skills.isNullOrEmpty() shouldBe false
        person.skills shouldBe Skills(
            skills = listOf(
                Skill(
                    skillLevel = skillAlgorithm.second,
                    description = skillAlgorithm.first,
                ),
                Skill(
                    skillLevel = skillJava.second,
                    description = skillJava.first,
                ),
                Skill(
                    skillLevel = skillKotlin.second,
                    description = skillKotlin.first,
                ),
            ),
        )

        person.languages.isNullOrEmpty() shouldBe false
        person.languages shouldBe Languages(
            languages = listOf(
                Language(
                    name = koreanLanguage.first,
                    level = koreanLanguage.second,
                ),
                Language(
                    name = englishLanguage.first,
                    level = englishLanguage.second,
                ),
            ),
        )
    }
})
