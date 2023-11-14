package step2.blackjack.model.vo

/**
 * 카드 받기 선택 여부
 * */
@JvmInline
value class ChoiceIsDeal(val choiceIsDeal: Boolean) {
    companion object {

        private const val AGREE = "y"
        private const val DISAGREE = "n"

        fun from(choiceIsDeal: String): ChoiceIsDeal {
            require(choiceIsDeal == AGREE || choiceIsDeal == DISAGREE) {
                "카드 받기 여부 문자는 ${AGREE}/${DISAGREE}만 사용해야 합니다."
            }
            return ChoiceIsDeal(choiceIsDeal == AGREE)
        }
    }
}