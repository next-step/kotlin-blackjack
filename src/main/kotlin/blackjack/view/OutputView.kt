package blackjack.view

import blackjack.model.Participants

object OutputView {

    fun presentCards(participants: Participants) {
        TODO("Not yet implemented")
        /*
        pobi카드: 2하트, 8스페이드
        jason카드: 7클로버, K스페이드
         */
    }

    fun result(participants: Participants) {
        presentCardsWitScore(participants)
        presetResult(participants)

    }

    private fun presetResult(participants: Participants) {
        /*
        ## 최종 승패
        딜러: 1승 1패
        pobi: 승
        jason: 패
         */
        TODO("Not yet implemented")
    }

    private fun presentCardsWitScore(participants: Participants) {
        TODO("Not yet implemented")
    }
}
