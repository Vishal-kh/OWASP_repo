var express = require('express')
var helmet = require('helmet')
var app = express()

function dnsPrefetchingNoncompliant() {
    // {fact rule=dns-prefetching-allowed@v1.0 defects=1}
    // ruleid:dns-prefetching-allowed
    app.use(
        helmet.dnsPrefetchControl({
            // Noncompliant: 'allow' is set to 'true'.
            allow: true
        })
    )
    // {/fact}
}


var express = require('express')
var helmet = require('helmet')
var app = express()

function dnsPrefetchingCompliant() {
    // {fact rule=dns-prefetching-allowed@v1.0 defects=0}
    // ok:dns-prefetching-allowed
    app.use(
        helmet.dnsPrefetchControl({
            // Compliant: 'allow' is set to 'false'.
            allow: false
        })
    )
    // {/fact}
}
