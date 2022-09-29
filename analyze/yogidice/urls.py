from django.urls import path
from . import views

urlpatterns = [
    path('bggdata_list', views.bggdata_list),
    path('boardgame_list', views.boardgame_list),
    path('recommend/detail/<int:game_id>', views.recommend_detail),
    path('하루다섯끼김현주/<slug:game_id>', views.search_rating),
    path('킹와플/<int:user>', views.get_user_data),
]