/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
var express = require('express')
var loginController = express.Router()
var jwt = require("jsonwebtoken")
function insufficientlyProtectedCredentialsNoncompliant() {
    loginController.post('/', async (req, res, next) => {
        var email = req.body.email
        // Noncompliant: JWT is not signed with a strong cipher algorithm.
        // {fact rule=jwt-insecure-algorithm@v1.0 defects=1}
        // ruleid:jwt-insecure-algorithm
        var token = jwt.sign(email, process.env.JWT_SECRET, { algorithm: 'none' })
        return token
    })
}
// {/fact}

var express = require('express')
var loginController = express.Router()
var jwt = require("jsonwebtoken")
function insufficientlyProtectedCredentialsCompliant() {
    loginController.post('/', async (req, res, next) => {
        var email = req.body.email
        // Compliant: JWT is signed with a strong cipher algorithm.
        // {fact rule=jwt-insecure-algorithm@v1.0 defects=0}
        // ok:jwt-insecure-algorithm
        var token = jwt.sign(email, process.env.JWT_SECRET, { algorithm: 'RS256' })
        return token
    })
}
// {/fact}