package blackjack.exception

class BizException(
    errorStatus: ErrorStatus
) : RuntimeException(errorStatus.getErrorMessage())
