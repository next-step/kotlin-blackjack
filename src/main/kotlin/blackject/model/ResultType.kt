package blackject.model

sealed class ResultType : Result {
    object AllBlackJack : ResultType() {
        override fun profit(amount: Amount): Double {
            return amount.value * EarningRate.AllBlackJack.rate
        }
    }

    object BlackJack : ResultType() {
        override fun profit(amount: Amount): Double {
            return amount.value * EarningRate.BlackJack.rate
        }
    }

    object Lose : ResultType() {
        override fun profit(amount: Amount): Double {
            return amount.value * EarningRate.Lose.rate
        }
    }

    object Win : ResultType() {
        override fun profit(amount: Amount): Double {
            return amount.value * EarningRate.Win.rate
        }
    }
}
