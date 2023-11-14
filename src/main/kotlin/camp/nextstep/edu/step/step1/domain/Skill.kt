package camp.nextstep.edu.step.step1.domain

sealed interface Skill {
    val introduce: String

    @JvmInline
    value class SoftSkill(
        override val introduce: String
    ) : Skill {

        init {
            require(introduce.isNotBlank()) { "소프트 스킬 이름이 입력되지 않았습니다." }
        }
    }

    @JvmInline
    value class HardSkill(
        override val introduce: String
    ) : Skill {

        init {
            require(introduce.isNotBlank()) { "하드 스킬 이름이 입력되지 않았습니다." }
        }
    }

}
