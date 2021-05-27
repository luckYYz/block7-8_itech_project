provider "aws" {
  region     = "eu-central-1"
  access_key = "***"
  secret_key = "***"
}

terraform {
  backend "s3" {
    bucket = "***"
    key    = "bs-state/"
    region = "eu-central-1"
    access_key = "***"
    secret_key = "***"
  }
}