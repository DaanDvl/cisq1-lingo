# CISQ1: Lingo Trainer
[![Java CI](https://github.com/DaanDvl/cisq1-lingo/actions/workflows/build.yml/badge.svg)](https://github.com/DaanDvl/cisq1-lingo/actions/workflows/build.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=DaanDvl_cisq1-lingo&metric=coverage)](https://sonarcloud.io/dashboard?id=DaanDvl_cisq1-lingo)


# Vulnerability Analysis

## A1:2017 Injection

### Description

Injectie is een manier van queries Injecteren bij bijvoorbeeld een SQL database, hiermee kan je tables droppen en data uitlezen die niet toegankelijk hoort te zijn.

### Risk

Het risico van Injection is relatief klein door de automatische sanitization die wordt gedaan door spring repositories.

### Counter-measures
Momenteel zijn er geen genomen counter-measures omdat er geen risico is op Injectie. Als ik in de toekomst meer zelf gemaakte SQL queries zou toevoegen, zou ik die doen met Prepared statements.

## A9:2017 Using Components with Known Vulnerabilities

### Description
Het gebruiken van code van andere mensen die bekende vulnerabilities bevatten, bijvoorbeeld doordat ze outdated zijn.

### Risk
Het risico van dit is het grootste van het project. Omdat we niet de veiligheid van andere dependencies kunnen controleren is er altijd een gevaar van misbruik van vulnerabilities.

### Counter-measures
Ik gebruik GitHub dependabot en dependencychecker in maven om te kijken of mijn libraries altijd de laatste versie zijn, zo loop ik nooit achter op security patches.


## A10:2017 Insufficient Logging & Monitoring

### Description
Gevaar van gehackt worden en het niet doorhebben, doordat er niet wordt gelogd wie in het systeem komt kan er een hack plaatsvinden en voor een lange tijd niet ontdekt worden.

### Risk
Het risico is relatief klein voor mijn applicatie, omdat ik geen data opsla die waardig is voor hackers. Er is geen punten systeem of login informatie.

### Counter-measures
Omdat de mogelijkheid om in te loggen nieteens bestaat is het onmogelijk voor een hacker om toegang te krijgen in het systeem naast de functies die open staan via REST. Daarom zijn er geen counter-measures genomen.
