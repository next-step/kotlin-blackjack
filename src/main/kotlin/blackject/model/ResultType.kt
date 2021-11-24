package blackject.model

/**
 * 게임 결과
 * */
enum class ResultType : Result {
    Bust {
        override fun rate(): EarningRate {
            return EarningRate.Bust
        }

        override fun profit(amount: Amount): Double {
            return EarningRate.Bust.rate
        }
    },
    BlackJack {
        override fun rate(): EarningRate {
            return EarningRate.BlackJack
        }

        override fun profit(amount: Amount): Double {
            return amount.value.times(EarningRate.BlackJack.rate)
        }
    },
    Lose {
        override fun rate(): EarningRate {
            return EarningRate.Lose
        }

        override fun profit(amount: Amount): Double {
            return amount.value.times(EarningRate.Lose.rate)
        }
    },
    Win {
        override fun rate(): EarningRate {
            return EarningRate.Win
        }

        override fun profit(amount: Amount): Double {
            return amount.value.times(EarningRate.Win.rate)
        }
    }
}
