resource "aws_lb" "ctg-lb" {
  name               = "ctg-lb"
  load_balancer_type = "application"
  internal           = false
  subnets            = ["***", "***"]
  security_groups = [aws_security_group.lb.id]
}

resource "aws_lb_target_group" "lb_target_group" {
  name     = "lb-tg-http"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = "***"
  target_type = "ip"

  health_check {
    path                = ""
    healthy_threshold   = 2
    unhealthy_threshold = 10
    timeout             = 60
    interval            = 300
    matcher             = "200,301,302"
  }
}

resource "aws_lb_listener" "ctg-listener" {
  load_balancer_arn = aws_lb.ctg-lb.arn
  port              = 443
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-2016-08"
  certificate_arn   = "****"
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.lb_target_group.arn
  }
}