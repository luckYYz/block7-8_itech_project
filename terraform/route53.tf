resource "aws_route53_record" "api" {
  zone_id = "Z017153216UWJSEQM4CK4"
  name    = "bs.timechamp.de"
  type    = "A"

  alias {
    name                   = aws_lb.ctg-lb.dns_name
    zone_id                = aws_lb.ctg-lb.zone_id
    evaluate_target_health = true
  }
}

resource "aws_route53_record" "www_api" {
  zone_id = "Z017153216UWJSEQM4CK4"
  name    = "www.bs.timechamp.de"
  type    = "A"

  alias {
    name                   = aws_lb.ctg-lb.dns_name
    zone_id                = aws_lb.ctg-lb.zone_id
    evaluate_target_health = true
  }
}