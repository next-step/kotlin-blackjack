package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class ResumeTest : StringSpec({

    "내 이름을 포함한 이력서를 만든다" {
        val expected = "김해중"

        val resume: Resume = introduce {
            name("김해중")
        }

        resume.name shouldBe expected
    }

    "내 이름과 회사 이름을 포함한 이력서를 만든다" {
        val expectedName = "김해중"
        val expectedCompany = "오리백숙집"

        val resume: Resume = introduce {
            name("김해중")
            company("오리백숙집")
        }

        resume.name shouldBe expectedName
        resume.company shouldBe expectedCompany
    }

    "내 이름, 회사, 기술을 포함한 이력서를 만든다" {
        val expectedName = "김해중"
        val expectedCompany = "오리백숙집"
        val expectedSoftSkills = listOf("잠자기", "숨시기")
        val expectedHardSkills = listOf("먹기")

        val resume: Resume = introduce {
            name("김해중")
            company("오리백숙집")
            skills {
                soft("잠자기")
                soft("숨시기")
                hard("먹기")
            }
        }

        resume.name shouldBe expectedName
        resume.company shouldBe expectedCompany
        resume.skills.shouldNotBeNull()
        resume.skills?.soft shouldContainExactly expectedSoftSkills
        resume.skills?.hard shouldContainExactly expectedHardSkills
    }

    "내 이름, 회사, 기술, 언어를 포함한 이력서를 만든다" {
        val expectedName = "김해중"
        val expectedCompany = "오리백숙집"
        val expectedSoftSkills = listOf("잠자기", "숨시기")
        val expectedHardSkills = listOf("먹기")
        val expectedKoreanLevel = "한국어" to 5
        val expectedEnglishLevel = "영어" to -3

        val resume: Resume = introduce {
            name("김해중")
            company("오리백숙집")
            skills {
                soft("잠자기")
                soft("숨시기")
                hard("먹기")
            }
            languages {
                "한국어" level 5
                "영어" level -3
            }
        }

        resume.name shouldBe expectedName
        resume.company shouldBe expectedCompany
        resume.skills.shouldNotBeNull()
        resume.skills?.soft shouldContainExactly expectedSoftSkills
        resume.skills?.hard shouldContainExactly expectedHardSkills
        resume.languages.shouldNotBeNull()
        resume.languages?.get(expectedKoreanLevel.first) shouldBe expectedKoreanLevel.second
        resume.languages?.get(expectedEnglishLevel.first) shouldBe expectedEnglishLevel.second
    }
})
