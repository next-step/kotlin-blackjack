package blackjack.domain

data class UserNames(val nameSet: Set<String>) : Iterable<String> by nameSet
