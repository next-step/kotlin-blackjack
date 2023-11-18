package dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
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

class DslTest {
    @Test
    fun nameTest() {
        val person = introduce {
            name(NAME)
        }
        person.name shouldBe NAME
    }

    @Test
    fun companyTest() {
        val person = introduce {
            company(COMPANY)
        }
        person.company shouldBe COMPANY
    }

    @Test
    fun skillTest() {
        val hardSkill = Skill(HARD_SKILL)
        val softSkill = Skill(SOFT_SKILL1)

        val person = introduce {
            skills {
                hard(hardSkill)
                soft(softSkill)
            }
        }
        person.skills.hard shouldBe listOf(hardSkill)
        person.skills.soft shouldBe listOf(softSkill)
    }

    @Test
    fun languageTest() {
        val person = introduce {
            languages {
                KOREAN level LEVEL5
            }
        }
        person.languages.languages shouldBe listOf(LANGUAGE1)
    }

    @Test
    fun totalTest() {
        val softSkill1 = Skill(SOFT_SKILL1)
        val softSkill2 = Skill(SOFT_SKILL2)
        val hardSkill = Skill(HARD_SKILL)

        val person = introduce {
            name(NAME)
            company(COMPANY)
            skills {
                soft(softSkill1)
                soft(softSkill2)
                hard(hardSkill)
            }
            languages {
                KOREAN level LEVEL5
                ENGLISH level LEVEL4
            }
        }

        person.name shouldBe NAME
        person.company shouldBe COMPANY
        person.skills.soft[0] shouldBe softSkill1
        person.skills.soft[1] shouldBe softSkill2
        person.skills.hard shouldBe listOf(hardSkill)
        person.languages.languages[0] shouldBe LANGUAGE1
        person.languages.languages[1] shouldBe LANGUAGE2
    }

    companion object {
        private const val NAME: String = "김강호"
        private const val COMPANY: String = "도레미파솔라시도"
        private const val SOFT_SKILL1: String = "잠자기"
        private const val SOFT_SKILL2: String = "띵가띵가"
        private const val HARD_SKILL: String = "밥먹기"
        private const val KOREAN: String = "Korean"
        private const val ENGLISH: String = "English"
        private const val LEVEL5: Int = 5
        private const val LEVEL4: Int = 4
        private val LANGUAGE1: Language = Language.from(KOREAN, LEVEL5)
        private val LANGUAGE2: Language = Language.from(ENGLISH, LEVEL4)
    }
}

// data class Person(val name: String, val company: String)
