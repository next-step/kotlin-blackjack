import dsl.Language
import dsl.Languages
import dsl.Skill
import dsl.Skills
import dsl.introduce
import dsl.of
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
        val hardSkillList = listOf(Skill(HARD_SKILL1))
        val softSkillList = listOf(Skill(SOFT_SKILL1), Skill(SOFT_SKILL2))

        val person = introduce {
            skills(
                Skills(
                    hard = hardSkillList,
                    soft = softSkillList
                )
            )
        }
        person.skills.hard shouldBe hardSkillList
        person.skills.soft shouldBe softSkillList
    }

    @Test
    fun languageTest() {
        val languageList = Languages(listOf(Language(KOREAN of LEVEL5), Language(ENGLISH of LEVEL4)))

        val person = introduce {
            languages(languageList)
        }
        person.languages shouldBe languageList
    }

//    @Test
//    fun pair() {
//        val pair1 = Pair(1, "one")
//        val pair2 = 1 to "one"
//        val pair3 = 1 of "one"
//    }

    companion object {
        private const val NAME: String = "김강호"
        private const val COMPANY: String = "도레미파솔라시도"
        private const val SOFT_SKILL1: String = "잠자기"
        private const val SOFT_SKILL2: String = "띵가띵가하기"
        private const val HARD_SKILL1: String = "밥먹기"
        private const val KOREAN: String = "korean"
        private const val ENGLISH: String = "english"
        private const val LEVEL5: Int = 5
        private const val LEVEL4: Int = 4
    }
}

// data class Person(val name: String, val company: String)
