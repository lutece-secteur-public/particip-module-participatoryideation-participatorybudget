/*
 * Copyright (c) 2002-2020, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.participatoryideation.modules.participatorybudget.service.export;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.participatoryideation.business.proposal.Idee;
import fr.paris.lutece.plugins.participatoryideation.business.proposal.IdeeHome;
import fr.paris.lutece.plugins.participatoryideation.service.IdeeWSService;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.resource.IResourceHistoryService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.util.AppLogService;

/**
 * 
 * This task exports the proposal as a project.
 *
 */
public class ExportToBudgetTask extends SimpleTask
{
    @Inject
    private IResourceHistoryService _resourceHistoryService;

    @Override
    public String getTitle( Locale locale )
    {
        return "Export as participatorybudget project";
    }

    // *********************************************************************************************
    // * PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS P *
    // * PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS PROCESS P *
    // *********************************************************************************************

    @Override
    public void processTask( int nIdResourceHistory, HttpServletRequest request, Locale locale )
    {
        Idee proposal = null;

        // Get proposal
        ResourceHistory resourceHistory = _resourceHistoryService.findByPrimaryKey( nIdResourceHistory );

        if ( resourceHistory == null )
        {
            AppLogService.error( "ExportToBudgetTask.processTask() method called with a unknown resource history id #" + nIdResourceHistory + " !" );
            return;
        }

        if ( !Idee.WORKFLOW_RESOURCE_TYPE.equals( resourceHistory.getResourceType( ) ) )
        {
            AppLogService.error( "ExportToBudgetTask.processTask() method called with a bad resource type '" + resourceHistory.getResourceType( )
                    + "' in resource history, should be '" + Idee.WORKFLOW_RESOURCE_TYPE + "'  !" );
            return;
        }

        proposal = IdeeHome.findByPrimaryKey( resourceHistory.getIdResource( ) );

        if ( proposal == null )
        {
            AppLogService.error(
                    "ExportToBudgetTask.processTask() method called with an unknown proposal #" + resourceHistory.getIdResource( ) + " in resource history !" );
            return;
        }

        // Validate operation is possible
        if ( proposal.getExportedTag( ) == 1 )
        {
            AppLogService.error( "ExportToBudgetTask.processTask() method called with a proposal marked as already exported !" );
            return;
        }

        // Do export
        try
        {
            int projectId = ExportToBudgetService.getInstance( ).exportToParticipatoryBudgetAction( proposal );

            // Attach proposal to project
            proposal.setIdProjet( Integer.toString( projectId ) );

            // Change state if export process did not thrown an exception
            proposal.setExportedTag( 1 );
            IdeeWSService.getInstance( ).updateIdee( proposal );
        }
        catch( Exception e )
        {
            AppLogService.error( "ExportToBudgetTask.processTask() thrown an exception, export aborted.", e );
            throw new RuntimeException( e ); // To ensure the workflow process will revert the task
        }

    }

}
