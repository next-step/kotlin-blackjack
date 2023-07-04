package blackjack.domain.result

import blackjack.model.DealerResultModel

data class DealerResult(val dealerResultModel: DealerResultModel = DealerResultModel()) {
    fun winCount(): Int {
        return dealerResultModel.winCount
    }

    fun loseCount(): Int {
        return dealerResultModel.loseCount
    }
}
