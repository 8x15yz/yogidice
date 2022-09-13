from django.contrib import admin
from .models import Test01 # 모델에서 Resource를 불러온다

# # 출력할 ResourceAdmin 클래스를 만든다
# class Test01Admin(admin.ModelAdmin):
#   list_display = ('id', 'name')

# 클래스를 어드민 사이트에 등록한다.
admin.site.register(Test01) #, Test01Admin)