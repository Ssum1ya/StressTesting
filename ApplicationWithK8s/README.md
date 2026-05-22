# Запуск кластера

## 1. Установить Nginx Ingress Controller
```bash
minikube addons enable ingress
# или для Docker Desktop / k3d:
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.10.0/deploy/static/provider/cloud/deploy.yaml
```

## 2. Собрать и загрузить образы

### Minikube
```bash
# Переключить Docker на Minikube
eval $(minikube docker-env)

# Собрать образы
docker build -t your-registry/service1:latest ./service1
docker build -t your-registry/service2:latest ./service2
```

### Docker Desktop
```bash
docker build -t your-registry/service1:latest ./service1
docker build -t your-registry/service2:latest ./service2
```

Замени `your-registry` на имя своего образа в файлах 04-service2.yaml и 05-service1.yaml.

## 3. Применить манифесты
```bash
kubectl apply -f k8s/
```

## 4. Проверить что всё запустилось
```bash
# Статус подов
kubectl get pods -n benchmark

# Должно быть:
# postgres-0        1/1 Running
# service1-xxx      1/1 Running  (3 штуки)
# service2-xxx      1/1 Running  (3 штуки)

# Статус HPA
kubectl get hpa -n benchmark

# Логи сервиса
kubectl logs -n benchmark deployment/service1
```

## 5. Добавить benchmark.local в hosts
```bash
# Узнать IP Ingress
kubectl get ingress -n benchmark

# Добавить в /etc/hosts (Mac/Linux) или C:\Windows\System32\drivers\etc\hosts (Windows)
127.0.0.1 benchmark.local
# или для Minikube:
$(minikube ip) benchmark.local
```

## 6. Запустить k6
```bash
k6 run -e VARIANT=webflux-http -e URL=http://benchmark.local test.js
```

## 7. Посмотреть как HPA скейлит поды
```bash
# В отдельном терминале — наблюдать за скейлингом в реальном времени
kubectl get hpa -n benchmark -w
```

## Полезные команды
```bash
# Принудительно выставить реплики (без HPA)
kubectl scale deployment service1 --replicas=3 -n benchmark

# Удалить всё
kubectl delete namespace benchmark

# Перезапустить деплой после пересборки образа
kubectl rollout restart deployment/service1 -n benchmark
```
