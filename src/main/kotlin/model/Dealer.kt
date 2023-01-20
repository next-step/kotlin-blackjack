package model

class Dealer : Person() {
    init {
        name = DEALER
    }

    companion object {
        private const val DEALER = "딜러"
    }
}
