from .models import (
    BggData,
    BoardGame,
    BoardGameCafe,
    Bookmark,
    CafeGameList,
    Category,
    CategoryGroup,
    History,
    Mechanism,
    MechanismGroup,
    Type,
    TypeGroup,
    User
)
from .serializers import (
    BggDataSerializer,              ## BggData
    BoardGameSerializer,            ## BoardGame
    BoardGameCafeSerializer,        ## BoardGameCafe
    BookmarkSerializer,             ## Bookmark
    CafeGameListSerializer,         ## CafeGameList
    CategorySerializer,             ## Category
    CategoryGroupSerializer,        ## CategoryGroup
    HistorySerializer,              ## History
    MechanismSerializer,            ## Mechanism
    MechanismGroupListSerializer,   ## MechanismGroup
    TypeSerializer,                 ## Type
    TypeGroupSerializer,            ## TypeGroup
    UserSerializer                  ## Type
) 
from rest_framework.decorators import api_view
from rest_framework.response import Response 
from rest_framework.status import (
    HTTP_201_CREATED,
    HTTP_204_NO_CONTENT,
)

from django.http import ( HttpResponse, JsonResponse)
from . import CateModel


@api_view(['GET'])
def bggdata_list(request):
    if request.method == 'GET':
        name = BggData.objects.all()
        serializer = BggDataSerializer(name, many=True)
        return Response(serializer.data)


@api_view(['GET'])
def boardgame_list(request):
    if request.method == 'GET':
        name = BoardGame.objects.all()
        serializer = BoardGameSerializer(name, many=True)
        return Response(serializer.data)

@api_view(['GET'])
def recommend_detail(request, game_id):
    model_result = CateModel.calccate(game_id)
    return JsonResponse({
        '1st' : model_result[0],
        '2nd' : model_result[1],
        '3rd' : model_result[2],
        '4th' : model_result[3],
        '5th' : model_result[4],
        '6th' : model_result[5],
        '7th' : model_result[6],
        '8th' : model_result[7],
        '9th' : model_result[8],
        '10th' : model_result[9]
    })