from django.contrib import admin
from controle.models import *
# from django.contrib.admin.widgets import AdminDateWidget


admin.site.register(Agence)
admin.site.register(Agent)
admin.site.register(Client)
admin.site.register(BordereauOp)


@admin.register(Operation)
class Operation(admin.ModelAdmin):
    list_display=('date', 'montant', 'refOp', 'service', 'agence', 'typeOp', 'client', 'operateur', 'agent', 'caisse')
    list_filter= ('refOp', 'date')
    
admin.site.register(Operateur)
admin.site.register(Approvisionnement)
admin.site.register(Nature)
admin.site.register(Orion)
admin.site.register(Direction)
admin.site.register(CompteOp)
# admin.site.register(Consolidation)
admin.site.register(TypeOperation)
admin.site.register(Caisse)
admin.site.register(Service)
admin.site.register(Fonction)
@admin.register(Consolidation)
class Consolidation(admin.ModelAdmin):
    list_display=('operation', 'bordereauOp', 'nature', 'date', 'reglement','datereglement')
    search_fields=('date', )
    
# class Agence(admin.ModelAdmin):
#     list_display=('NoAgence','nom', 'tel', 'mail', 'localisation')
#     search_fields=('NoAgence','nom' )
    
# Register your models here.

