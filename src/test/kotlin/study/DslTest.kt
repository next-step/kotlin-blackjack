package study

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class DslTest: FreeSpec({
    "이름 입력 Dsl 테스트" - {
        listOf("김설영", "하하하")
            .forEach { name ->
                "입력값: $name" {
                    val person = introduce {
                        name(name)
                    }

                    person.name shouldBe name
                }
            }
    }

    "회사 입력 Dsl 테스트" - {
        listOf("카카오뱅크", "토스뱅크")
            .forEach { company ->
                "입력값: $company" {
                    val person = introduce {
                        name("김설영")
                        company(company)
                    }

                    person.company shouldBe company
                }
            }
    }

    "skills 입력 테스트" - {
        listOf(
            listOf("문제 해결에 진심", "좋은 커뮤니케이션 스킬", "코틀린"),
            listOf("근본적인 해결법을 찾으려고 함", "좋은 커뮤니케이션 스킬", "자바"),
        ).forEach { skills ->
                "입력값: $skills" {
                    val person = introduce {
                        name("김설영")
                        company("카카오뱅크")
                        skills {
                            soft(skills[0])
                            soft(skills[1])
                            hard(skills[2])
                        }
                    }

                    person.skills shouldBe listOf(SoftSkill(skills[0]), SoftSkill(skills[1]), HardSkill(skills[2]))
                }
            }
    }

    "language 입력 테스트" - {
        listOf(
            listOf(Pair("Korean", 5), Pair("English", 3)),
            listOf(Pair("Japanese", 10), Pair("French", 13)),
        ).forEach { languageLevelPair ->
            "입력값: $languageLevelPair" {
                val language1 = languageLevelPair[0].first
                val level1 = languageLevelPair[0].second

                val language2 = languageLevelPair[1].first
                val level2 = languageLevelPair[1].second

                val person = introduce {
                    name("김설영")
                    company("카카오뱅크")
                    skills {
                        soft("문제 해결에 진심")
                        soft("좋은 커뮤니케이션 스킬")
                        hard("코틀린")
                    }
                    languages {
                        language1 level level1
                        language2 level level2
                    }
                }

                person.languages shouldBe listOf(Language(language1, level1), Language(language2, level2))
            }
        }
    }
})