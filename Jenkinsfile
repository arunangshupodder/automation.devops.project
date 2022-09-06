pipeline {
    // needed to run jenkins in non-linux machines.
    // executor count for master node must be set to 0.
    agent any
    parameters {
        choice(name: 'test_suite',
            choices: 'SmokeTestsSuite\nRegressionTestsSuite\nIntegrationTestsSuite',
            description: 'Select the test suite for execution.')
        choice(name: 'environment',
            choices: 'dev\ntest\nprod',
            description: 'Select the environment for execution.')
        choice(name: 'browser_type',
            choices: 'chrome\nfirefox',
            description: 'Select the browser for execution.')
    }
    stages {
        // this stage stops running services and clears files from previous executions
        stage('Checkout Code') {
            steps {
                git 'https://github.com/arunangshupodder/automation.devops.project.git'
            }
        }
        stage ('Run Tests') {
            steps {
                sh "mvn clean install -Dtestng.file.name=${params.test_suite}.xml -Denv=${environment} -Dbrowser=${browser_type}"
            }
        }
    }
}