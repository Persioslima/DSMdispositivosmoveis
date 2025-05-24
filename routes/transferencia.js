const express = require('express');
const router = express.Router();
const controller = require('../controllers/transferenciaController');

router.post('/criptografar', controller.criptografarDados);
router.post('/descriptografar', controller.descriptografarDados);
router.post('/transferir', controller.transferir);
router.post('/transferir_vulneravel', controller.transferirVulneravel);

module.exports = router;
