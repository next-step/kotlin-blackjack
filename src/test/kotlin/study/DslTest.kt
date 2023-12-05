package study

import introduce.domain.introduce
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

/**
 * introduce {
 *   name("알렉스")
 *   company("CJ올리브영")
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
class DslTest : StringSpec({

    "이름, 회사 정보를 입력할 경우 입력한 정보가 담긴 Person 객체를 반환한다." {
        forAll(
            row("알렉스", "CJ올리브영"),
            row("제이슨", "우아한형제들"),
            row("홍길동", "활빈당")
        ) { name: String, company: String ->
            val person = introduce {
                name(name)
                company(company)
            }

            person.name shouldBe name
            person.company shouldBe company
        }
    }

    "이름, 회사, 스킬 정보를 입력할 경우 입력한 정보가 담긴 Person 객체를 반환한다." {
        forAll(
            row("알렉스", "CJ올리브영", "Kotlin", "Java"),
            row("제이슨", "우아한형제들", "Nothing", "All"),
            row("홍길동", "활빈당", "나쁜짓", "착한짓")
        ) { name: String, company: String, softSkill: String, hardSkill: String ->
            val person = introduce {
                name(name)
                company(company)
                skills {
                    soft(softSkill)
                    hard(hardSkill)
                }
            }

            person.name shouldBe name
            person.company shouldBe company
            person.skills.soft shouldContain softSkill
            person.skills.hard shouldContain hardSkill
        }
    }

    "스킬 정보는 중복해서 입력할 수 있다." {
        forAll(
            row("알렉스", "CJ올리브영", "Kotlin", "Infra", "Java"),
            row("홍길동", "활빈당", "A passion for problem solving", "Good communication skills", "Kotlin")
        ) { name: String, company: String, softSkill1: String, softSkill2: String, hardSkill: String ->
            val person = introduce {
                name(name)
                company(company)
                skills {
                    soft(softSkill1)
                    soft(softSkill2)
                    hard(hardSkill)
                }
            }

            person.name shouldBe name
            person.company shouldBe company
            person.skills.soft shouldContain softSkill1
            person.skills.soft shouldContain softSkill2
            person.skills.hard shouldContain hardSkill
        }
    }

    "이름, 회사, 스킬, 언어 정보를 입력할 경우 입력한 정보가 담긴 Person 객체를 반환한다." {
        forAll(
            row("알렉스", "CJ올리브영", "Kotlin", "Java", 5, 1),
            row("제이슨", "우아한형제들", "Nothing", "All", 10, 10),
            row("홍길동", "활빈당", "나쁜짓", "착한짓", 10, 0)
        ) { name: String, company: String, softSkill: String, hardSkill: String, koreanLevel: Int, englishLevel: Int ->
            val person = introduce {
                name(name)
                company(company)
                skills {
                    soft(softSkill)
                    hard(hardSkill)
                }
                languages {
                    "Korean" level koreanLevel
                    "English" level englishLevel
                }
            }

            person.name shouldBe name
            person.company shouldBe company
            person.skills.soft shouldContain softSkill
            person.skills.hard shouldContain hardSkill
            person.languages["Korean"] shouldBe koreanLevel
            person.languages["English"] shouldBe englishLevel
        }
    }
})
