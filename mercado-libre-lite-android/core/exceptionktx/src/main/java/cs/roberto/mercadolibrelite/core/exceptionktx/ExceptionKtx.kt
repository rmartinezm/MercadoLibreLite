package cs.roberto.mercadolibrelite.core.exceptionktx

/* */
val Exception.messageOrClassName: String
    get() = message ?: javaClass.simpleName