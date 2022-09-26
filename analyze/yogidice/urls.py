from django.urls import path
from . import views

urlpatterns = [
    path('bggdata_list', views.bggdata_list),
    path('boardgame_list', views.boardgame_list),
]