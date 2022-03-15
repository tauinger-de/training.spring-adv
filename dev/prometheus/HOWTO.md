# Run Prometheus

From project root folder:
````shell
docker run -d --name=prometheus -p 9090:9090 -v ./dev/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus 
````