# ecs.tf

resource "aws_ecs_cluster" "ctg" {
  name = "ctg-cluster"
}

resource "aws_ecs_task_definition" "ctg" {
  family                   = "ctg-task"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "512"
  memory                   = "1024"
  container_definitions = file("container-definitions/container-def.json")
}

resource "aws_ecs_service" "ctg" {
  name            = "ctg-service"
  cluster         = aws_ecs_cluster.ctg.id
  task_definition = aws_ecs_task_definition.ctg.arn
  desired_count   = 1
  launch_type     = "FARGATE"
  force_new_deployment = true

  network_configuration {
    security_groups  = [aws_security_group.ecs_tasks.id]
    subnets          = ["***", "***"]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.lb_target_group.id
    container_name   = "ctg"
    container_port   = 8080
  }
  depends_on  = [aws_lb_listener.ctg-listener]
}