echo Initializing k8s resources

#kubectl apply -f adminer-deploy.yaml,enigmiservicedb-deploy.yaml,connessioniservicedb-deploy.yaml,enigmiseguitiservicedb-deploy.yaml,consul-deploy.yaml,zookeeper-deploy.yaml,kafka-deploy.yaml,apigateway-deploy.yaml,apigateway-ingress.yaml,enigmi-deploy.yaml,connessioni-deploy.yaml,enigmiseguiti-deploy.yaml
kubectl apply -f '../resources/*.yaml'