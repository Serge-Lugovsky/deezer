properties([disableConcurrentBuilds()])

pipeline {

    agent {
        label 'master'
    }

    options {
        timestamps()
    }

    triggers { cron('H/5 * * * *') }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORTS_DIR = 'target/allure-reports'
        GIT_REPOSITORY_URL = 'https://github.com/Serge-Lugovsky/deezer'
        GIT_BRANCH = 'master'

        CHROMEDRIVER = "wget --no-check-certificate 'https://drive.google.com/uc?authuser=0&id=1TPA99YSFvc5EsND6MgjqjbJt-aEtK-Pj&export=download' -O chromedriver"
        APP_PROPERTIES = "wget --no-check-certificate 'https://drive.google.com/uc?authuser=0&id=1eW71TiEi7Yo1j4c2Csfd-U4Z168pCgUR&export=download' -O app.properties"
        ALLURE_PROPERTIES = "wget --no-check-certificate 'https://drive.google.com/uc?authuser=0&id=1BJ8Kyb-bcmdkveU8MTK9Hjrg-Io5xrWE&export=download' -O allure.properties"
    }

    stages {

        stage('Get source code') {
            steps {
                git branch: "${GIT_BRANCH}", url: "${GIT_REPOSITORY_URL}"
            }
        }

        stage('Get resources') {
            steps {
                sh "cd src/main/resources/ && ${CHROMEDRIVER} && ${APP_PROPERTIES} && ${ALLURE_PROPERTIES} && chmod 777 *"
            }
        }

        stage('Testing') {
            steps {
                sh "mvn clean test -Pchrome -Pheadless"
            }
        }

        stage('Reports') {
            steps {
                allure([
                        includeProperties: false,
                        jdk              : "java-1.8",
                        report           : "${ALLURE_REPORTS_DIR}",
                        results          : [[path: "${ALLURE_RESULTS_DIR}"]]
                ])
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }

}
